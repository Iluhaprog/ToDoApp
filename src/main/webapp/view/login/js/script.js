let errorMsg = document.getElementById("error");

function showError(error) {
    if (error == "true") {
        errorMsg.style.opacity = 1;
    } else {
        errorMsg.style.opacity = 0; 
    }
}

let errorMsgR = document.getElementById("errorR");
let success = document.getElementById("success");

function showErrorR(error) {
    if (error == "true") {
        errorMsgR.style.opacity = 1;
    } else {
        success.style.opacity = 1; 
        setTimeout(function(){
            success.style.opacity = 0; 
        }, 2000);
    }
}


let form = document.getElementById("form-1");

function submitForm() {
    let params = "name=" + form[0].value + "&pass=" + form[1].value;
    let url  = "http://localhost:8080/ToDoApp/login?" + params;
    console.log(url);
    let req = new XMLHttpRequest();

    req.open("POST", url, true);
    req.send({});
    req.onload = function() {
        showError(req.getResponseHeader("error"));
        if (req.status == 200) {
            document.location.href = "http://localhost:8080/ToDoApp/user/page";
        }
        console.log(req);
    }
}


window.history.pushState(null, "login", "/ToDoApp/login/page");

function registration() {
    let rName = document.getElementById("r-name").value;
    let rPass = document.getElementById("r-pass").value;

    let url = "http://localhost:8080/ToDoApp/registration?name=" + rName + "&pass=" + rPass;
    let req = new XMLHttpRequest();
    req.open("POST", url, true);
    req.onload = function() {
        showErrorR(req.getResponseHeader("exist"));
        if (req.status == 200) {
            animForm();
        }
    }
    req.send({});
}

function animForm() {
    form1.classList.remove("block-form-1__hide-c");
        setTimeout(function(){
            form1.classList.remove("block-form-1__hide-d");
        }, 300);
        form2.classList.remove("block-between__lf");
        animateForm2();

        form3.style.opacity = "0";
        setTimeout(function() {
            form3.style.display = "none";
        }, 300);

        tl2.style.opacity = "0";
        setTimeout(function() {
            tl2.style.display = "none";
            tl1.style.display = "block";
        }, 300);
        tl1.style.opacity = "1";

        btn1.style.opacity = "0";

        setTimeout(function() {
            btn1.style.opacity = "1"
            bt1.style.display = "block";
            bt2.style.display = "none";
        }, 300);
        btn1.dataset.clickCounter = 0;
}