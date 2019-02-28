const url = "/api/games";
const divList = document.getElementById("divList");
const divLeaderboard = document.getElementById("leaderBoard");
const loggedScreen = document.getElementById("loggedScreen");
const loggedOffScreen = document.getElementById("loggedOff");
const welcomeText = document.getElementById("welcome");
const signUpBtn = document.getElementById("signUpBtn");
const signUpBtns = document.getElementById("signupbtns");
const logInBtn = document.getElementById("logInBtn");
const logInBtns = document.getElementById("loginbtns");


const getData = (url) => fetch(url);


getData(url).
    then(data => data.json()).
    then(result => {
        console.log(result)
        makeList(result.games)
        createLeaderBoard(result.leaderboard)
        changeDisplay(result)}).
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
    listElement.textContent =`${item.created} _  ${getGamePlayers(item.gameplayers)}`;
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
        return `${player1} vs ${player2}`;
}

function createElement(el,parentEl){
    let element = document.createElement(el);
    parentEl.appendChild(element);
    return element;
}

function createLeaderBoard(leaderboard){
    let table = createElement("table",divLeaderboard);
    createLeaderBoardHeader(table);
    let tbody = createElement("tbody",table);
    leaderboard.forEach(function createRow(player){
        // console.log(player.scores)
        if(Object.keys(player.scores).length === 0 && (player.scores).constructor === Object){
            console.log("Object its empty");
        }else{
            let tr = createElement("tr",tbody);
            let td = createElement("td",tr);
            td.textContent = player.player;
            createLeaderboardColumn(player,"win",tr);
            createLeaderboardColumn(player,"lose",tr);
            createLeaderboardColumn(player,"tie",tr);
            createLeaderboardColumn(player,"total",tr);

        }
    })
}

function createLeaderboardColumn(player,status,row){
    let td = createElement("td",row);
    if (player.scores[status]){
        td.textContent =player.scores[status];
    }else{
        td.textContent = "/";
    }
}

function createLeaderBoardHeader(table){
    let thead = createElement("thead",table);
    let header = createElement("tr",thead);
    let name = createElement("th",header);
    name.textContent = "Player";
    let win = createElement("th",header);
    win.textContent = "Win";
    let lose = createElement("th",header);
    lose.textContent = "Lose";
    let tie = createElement("th",header);
    tie.textContent = "Tie";
    let total = createElement("th",header);
    total.textContent = "total";
}

// const modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
// modal.onclick = modal.style.display = "none";

function login(){
    // event.preventDefault();
    const username = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // cant make JSON.stringify with encoded password.
//     fetch("/api/login", {
//         credentials: 'include',
//         headers: {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json',
//         },
//         method: 'POST',
//         body: JSON.stringify({userName: username, password : password}),
//     })
//         .then(r => {
//         if (r.status == 200) {
//         window.location.reload()
//     } else {
//         console.log("Username or Password are wrong");
//     }
// })
// .catch(e => console.log(e))

    fetch("/api/login", {
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        method: 'POST',
        body: 'userName='+ username + '&password='+ password,
    })
        .then(function (data) {
            console.log('Request success: ', data);
            location.reload();
        })
        .catch(function (error) {
            console.log('Request failure: ', error);
        });
}

function changeDisplay(data){
    (data.currentUser ? (loggedScreen.style.display = 'block', loggedOffScreen.style.display = 'none', welcomeMessage(data)) : (loggedScreen.style.display = 'none', loggedOffScreen.style.display = 'block'));

}

let welcomeMessage = (data) => welcomeText.textContent =`Welcome ${data.currentUser.email.split("@")[0]}`;

function loginOrSignup(){
    console.log("hi");
    ( logInBtn.style.display != 'none' ? (logInBtn.style.display = 'none', logInBtns.style.display = 'none', signUpBtn.style.display ='block', signUpBtns.style.display ='block' ) : (logInBtn.style.display = 'block', signUpBtn.style.display ='none',logInBtns.style.display = 'block', signUpBtns.style.display ='none') )

}

function signUp(){
    // event.preventDefault();
    const username = document.getElementById("email").value;
    const pass = document.getElementById("password").value;
    console.log(username,pass)
    fetch("/api/players", {
        credentials: 'include',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        method: 'POST',
        body: JSON.stringify({userName: username, password: pass}),
    })
        .then(function (response) {
            if (response.status == 201){
                login();
                return response;
            }else{
                console.log(response.status)
            }
        })
        .catch(function (error) {
            console.log('Request failure: ', error.message);
        });
}

function logOut(){
    //Can't make it work with json type
    // fetch("/api/logout", {
    //     credentials: 'include',
    //     headers: {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json'
    //     },
    //     method: 'POST',
    // })
    //     .then(function (response) {
    //         if (response.ok){
    //             return response.json();
    //         }
    //     }).then(function () {
    //     console.log("this should be just for succes")
    //     window.location.reload();
    // })
    //     .catch(function (error) {
    //         console.log('Request failure: ', error.message);
    //     });
    fetch("/api/logout", {
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        method: 'POST',
    })
        .then(function (data) {
            console.log('Request success: ', data);
            window.location.reload();

        })
        .catch(function (error) {
            console.log('Request failure: ', error);
        });

}