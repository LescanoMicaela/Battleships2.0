const url = "/api/games";
const divList = document.getElementById("divList");
const divLeaderboard = document.getElementById("leaderBoard");


const getData = (url) => fetch(url);


getData(url).
    then(data => data.json()).
    then(result => {
        console.log(result)
        makeList(result.games)
    createLeaderBoard(result.leaderboard)}).
    catch(error => console.log(error));


function makeList(games){
    let list = document.createElement("ol");
    games.forEach(
        (game) => createListElements(game,list))
    divList.appendChild(list);
}

function createListElements(item,list){
    let listElement = document.createElement("li");
    // console.log(item.gameplayers);
    listElement.textContent =item.created +" _ " + getGamePlayers(item.gameplayers);
    list.appendChild(listElement);
}

function getGamePlayers(gamePlayers){
    let player1 = gamePlayers[0].player.email;
    let player2;
    if(gamePlayers.length === 2){
        player2 = gamePlayers[1].player.email;
    }else{
        player2 = "waiting for oponent";
        }
        return player1 +" vs " + player2;
}

function createElement(el,parentEl){
    let element = document.createElement(el);
    parentEl.appendChild(element);
    return element;
}

function createLeaderBoard(leaderboard){
    let table = createElement("table",divLeaderboard);
    let thead = createElement("thead",table);
    let tbody = createElement("tbody",table);

    leaderboard.forEach(function createRow(player){
        console.log(player.score)
        // if(player.score != "");
    })
}