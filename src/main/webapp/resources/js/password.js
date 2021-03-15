const btn = document.querySelector('button[type="submit"]');
const error = document.querySelector("#error");

setTimeout(()=>{
    error.classList.add("hidden");
    const h3 = error.querySelector("h3");
    h3.innerText="Hasła nie pasują do siebie!";
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