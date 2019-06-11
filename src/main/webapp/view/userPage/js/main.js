let button = document.getElementById("nav-btn");

let nav = document.getElementById("nav");

button.addEventListener("click", function(){

    if(nav.dataset.show == "false") {
        nav.style.left = 0 + "px";
        nav.dataset.show = "true";
    } else {
        nav.style.left = -265 + "px";
        nav.dataset.show = "false";
    }
});

let addD = document.getElementById("add-d");
let textarea = document.getElementById("ta");
let addDoingBlock = document.getElementById("add-doing-block");

addD.addEventListener("click", function(){
    
    nav.style.left = -265 + "px";
    nav.dataset.show = "false";
    
    if (addDoingBlock.dataset.show == "false") {
        addDoingBlock.style.display = "block";
        addDoingBlock.style.opacity = 1;
        addDoingBlock.dataset.show = "true";
    } else {
        ta.value = "";
        addDoingBlock.style.opacity = 0;
        setTimeout(function() {
            addDoingBlock.style.display = "block";
        }, 500);
        addDoingBlock.dataset.show = "false";
    }
});

let cancelB = document.getElementById("cansel-btn");

cancelB.addEventListener("click", function(){
    if(addDoingBlock.dataset.show != "false"){
        ta.value = "";
        addDoingBlock.style.opacity = 0;
        setTimeout(function() {
            addDoingBlock.style.display = "block";
        }, 500);
        addDoingBlock.dataset.show = "false";
    }
});

let nad = document.getElementById("nad");
let ncd = document.getElementById("ncd");
let nncd = document.getElementById("nncd");

window.onload = function() {
    let xhr = new XMLHttpRequest();

    xhr.open("GET", "http://localhost:8080/ToDoApp/user", true);
    xhr.onload = function() {
        let doings = xhr.responseText;
        doings = JSON.parse(doings);
        let doingsBlock = document.getElementById("doings") 

        for (var i = doings.length - 3; i >= 0; i--) {
            doingsBlock.children[0].innerHTML += 
            `<div class="doing"> 
                <div class="row row__js-sb doing-info">
                    <h3>` + doings[i].doingText + `</h3>
                    <div class="completed" data-completed="` + doings[i].completed + `"><span><i class="fas fa-check"></i></span><span><i class="fas fa-times"></i></span></div>
                </div>
                <div class="row doing-btns">
                    <button class="compl-btn" data-id="` + doings[i].doingId + `"><i class="fas fa-clipboard-check"></i></button>
                    <button class="del-btn" data-id="` + doings[i].doingId + `"><i class="fas fa-trash"></i></button>    
                </div>
            </div>`
        } 

        let userName = document.getElementById("userName");
        let name = doings[doings.length - 2].userName;

        userName.innerHTML = name;

        document.title = name;

        nad.innerHTML = doings[doings.length - 1].nad;
        ncd.innerHTML = doings[doings.length - 1].ncd;
        nncd.innerHTML = doings[doings.length - 1].nncd;

        setCompleted();
        changeDoing();
        deleteDoing();
        window.history.pushState(null, name, "/ToDoApp/user/page");
    }
    xhr.send();
}

function setCompleted() {
    let compls = document.getElementsByClassName("completed");
    let btns = document.getElementsByClassName("compl-btn");

    for (var i = 0; i < compls.length; i++) {
        if (compls[i].dataset.completed == "true") {
            compls[i].children[0].style.display = "block";
            compls[i].children[1].style.display = "none";

            btns[i].setAttribute("disabled","disabled");
        } 
        if(compls[i].dataset.completed == "false") {
            compls[i].children[0].style.display = "none";
            compls[i].children[1].style.display = "block";
        }
    }
}

function changeDoing() {
    // У элементов с классом completed есть атрибут completed, 
    // поэтому мы используем массив из элементов с классом completed,
    // если completed = false то мы позволяем его пометить как 
    // выполненный нажав button с классом compl-btn

    let compls = document.getElementsByClassName("completed");
    let btns = document.getElementsByClassName("compl-btn");

    for (var i = compls.length - 1; i >= 0; i--) {
        if(compls[i].dataset.completed == "false") {
            btns[i].addEventListener("click", function() {
                let req = new XMLHttpRequest();
                let compl = true;
                let url = "http://localhost:8080/ToDoApp/doing/change?id=" + this.dataset.id + "&completed=" + compl;
                
                console.log(this.dataset.id);

                req.open("POST", url, true);

                req.send({});
                getDoings();
            });
        }
    }
}

function getDoings() {
    let req = new XMLHttpRequest();

    let url = "http://localhost:8080/ToDoApp/doing/get/user";
    req.open("GET", url, true);
    req.onload = function() {
        let doings = req.responseText;
        doings = JSON.parse(doings);

        let doingsBlock = document.getElementById("doings");
        doingsBlock.children[0].innerHTML = "";

        for (var i = doings.length - 2; i >= 0; i--) {
            doingsBlock.children[0].innerHTML += 
            `<div class="doing"> 
                <div class="row row__js-sb doing-info">
                    <h3>` + doings[i].doingText + `</h3>
                    <div class="completed" data-completed="` + doings[i].completed + `"><span><i class="fas fa-check"></i></span><span><i class="fas fa-times"></i></span></div>
                </div>
                <div class="row doing-btns">
                    <button class="compl-btn" data-id="` + doings[i].doingId + `"><i class="fas fa-clipboard-check"></i></button>
                    <button class="del-btn" data-id="` + doings[i].doingId + `"><i class="fas fa-trash"></i></button>    
                </div>
            </div>`;
        }
        
        nad.innerHTML = doings[doings.length - 1].nad;
        ncd.innerHTML = doings[doings.length - 1].ncd;
        nncd.innerHTML = doings[doings.length - 1].nncd;

        setCompleted(); 
        changeDoing();
        deleteDoing();
    }
    req.send();
}

function sendNewDoing() {
    let req = new XMLHttpRequest();
    
    let text = document.getElementById("ta").value;
    let url = "http://localhost:8080/ToDoApp/doing/insert?text=" + text;

    req.open("POST", url, true);
    req.onload = function() {
        getDoings();
    }
    req.send({});
}

function deleteDoing() {

    let delBtns = document.getElementsByClassName("del-btn");

    for (var i = 0; i < delBtns.length; i++) {
        delBtns[i].addEventListener("click", function() {
            let req = new XMLHttpRequest();
            let id = this.dataset.id;
            let url = "http://localhost:8080/ToDoApp/doing/delete?id=" + id;

            req.open("POST", url, true);
            req.onload = function() {
                getDoings();
            }

            req.send({});
        });
    }
}

let logOutBtn = document.getElementById("log-out");

logOutBtn.addEventListener("click", function(){
    let req = new XMLHttpRequest();
    let url = "http://localhost:8080/ToDoApp/logout";

    req.open("GET", url, true);
    req.onload = function() {
        location.reload();
    }
    req.send({});
});