let form1 = document.getElementById("f1");
let form2 = document.getElementById("f2");
let form3 = document.getElementById("f3");

let btn1 = document.getElementById("btn1");

let bt1 = document.getElementById("bt1");
let bt2 = document.getElementById("bt2");

let tl1 = document.getElementById("tl1");
let tl2 = document.getElementById("tl2");

function animateForm2() {
    form2.style.width = 450 + "px";
    setTimeout(function() {
        form2.style.width = 350 + "px";
    }, 300);
}

tl2.style.display = "none";

btn1.addEventListener("click", function(){
    if (btn1.dataset.clickCounter == 0) {
        form1.classList.add("block-form-1__hide-c");
        setTimeout(function(){
            form1.classList.add("block-form-1__hide-d");
        }, 300);
        form2.classList.add("block-between__lf");
        animateForm2();

        form3.style.display = "flex";
        form3.style.opacity = "1";

        tl1.style.opacity = "0";
        setTimeout(function() {
            tl1.style.display = "none";
            tl2.style.display = "block";
        }, 300);
        tl2.style.opacity = "1";

        btn1.style.opacity = "0";

        setTimeout(function() {
            btn1.style.opacity = "1"
            bt1.style.display = "none";
            bt2.style.display = "block";
        }, 300);
        btn1.dataset.clickCounter = 1;
    } else {
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

    console.log("click!");
});