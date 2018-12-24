const alpha = ["A","B","C","D","E","F","G","H","I","J"];
const numeric = ["1","2","3","4","5","6","7","8","9","10"];
const table = document.getElementById("playerGrid");
let gamePlayerID;

const getData = (url) => fetch(url);

getData(makeUrl()).
then(data => data.json()).
then(response => {console.log(response);
    createGrid()
    showShipsLocation(response)})
// .catch(error => console.error(error));


// this function gets the parameter gp ID, so we can later use it for AJAX call
function getParameterByName(name) {
    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
}

function makeUrl() {
    gamePlayerID = getParameterByName("gp");
    return '/api/game_view/' + gamePlayerID;
}

function createGrid(){
    let thead =  createElement("thead",table);
    let headRow = createElement("tr",thead);
    let firstTd = createElement("th",headRow);
    let tbody = createElement("tbody",table);
    for (let h = 0; h < 10; h++){
        let letterCol = createElement("th",headRow).textContent =  alpha[h];
    }
    for (let i = 0; i < 10; i++){
        let row = createElement("tr",tbody);
        let numCol = createElement("td",row);
        numCol.textContent = numeric[i];
        numCol.setAttribute("class","colNum");

        for (let c=0; c < 10; c++){
            let gridLocation = createElement("td",row);
            gridLocation.setAttribute("id",alpha[c] + numeric[i]);
        }
    }
}
function getPlayer(data){
    for (let i = 0; i < data.gamePlayers; i++){
        if(data.id == data.gamePlayers[i].id){
            return data.gamePlayers[i].player.email;
        }
    }
}
function getOponent(data){
    if ( data.gamePlayers.length > 1){
        for (let i = 0; i < data.gamePlayers; i++){
            if(data.id != data.gamePlayers[i].id){
                return data.gamePlayers[i].player.email;
            }
        }
    }
}

function showShipsLocation(data){
    data.ships.forEach( function getLocation(ship){
        ship.location.forEach(function(loc){
            let shipLocation = document.getElementById(loc);
            shipLocation.setAttribute("data-type", ship.type);
            shipLocation.setAttribute("class", "ship");
        })
    })
}

function createElement(el,parentEl){
    let element = document.createElement(el);
    parentEl.appendChild(element);
    return element;
}




