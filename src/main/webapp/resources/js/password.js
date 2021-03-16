const btn = document.querySelector('#submit-btn');
const error = document.querySelector("#error");

console.log(btn)

setTimeout(()=>{
    error.classList.add("hidden");
    const firstChild = error.firstChild;
    firstChild.innerText="Hasła nie pasują do siebie!";
},3000)

btn.addEventListener("click", e=>{
    const newPasswd = document.querySelector("#newPassword");
    const confirmPasswd = document.querySelector("#confirmPassword");
    if(newPasswd.value!==confirmPasswd.value){
        e.preventDefault();
        error.classList.remove("hidden");

        setTimeout(()=>{
            error.classList.add("hidden")
        }, 3000);
    }
})