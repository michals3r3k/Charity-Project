const btn = document.querySelector("#toSummary");

//Get Steps
const step1 = document.querySelector("div[data-step='1']");
const step2 = document.querySelector("div[data-step='2']");
const step3 = document.querySelector("div[data-step='3']");
const step4 = document.querySelector("div[data-step='4']");
const step5 = document.querySelector("div[data-step='5']");

//Get inputs
const checkboxes1 = step1.querySelectorAll("input");
const bagsInput2 = step2.querySelector("input");
const radio3 = step3.querySelectorAll("input");

const street4 = step4.querySelector("#street");
const city4 = step4.querySelector("#city");
const zipCode4 = step4.querySelector("#zipCode");
const phoneNumber4 = step4.querySelector("#phoneNumber")
const pickUpDate4 = step4.querySelector("#pickUpDate");
const pickUpTime4 = step4.querySelector("#pickUpTime");
const pickUpComment4 = step4.querySelector("#pickUpComment");

//Get SummaryTarget
const numberCategories = step5.querySelector("#numberCategories");
const forFundation = step5.querySelector("#forFundation");
const addressUl = step5.querySelector("#addressUl");
const termUl = step5.querySelector("#termUl");

btn.addEventListener("click", () => {
    let bags;
    let categories;
    let institution;
    let street;
    let city;
    let postCode;
    let phone;
    let date;
    let time;
    let pickUpComment;

    addressUl.innerHTML = '';
    termUl.innerHTML = '';

    (function refreshParams() {
        bags = bagsInput2.value;
        categories = ""
        institution = ""
        checkboxes1.forEach(el => {
            if (el.checked) categories += el.dataset.category + ', ';
        })
        categories = categories.slice(0, -2)

        radio3.forEach(el => {
            if (el.checked) institution = el.dataset.institution;
        })

        street = street4.value;
        city = city4.value;
        postCode = zipCode4.value;
        phone = phoneNumber4.value;
        date = pickUpDate4.value;
        time = pickUpTime4.value;
        pickUpComment = pickUpComment4.value;

    })()

    numberCategories.innerHTML = bags + ' worki: ' + categories + '.';
    forFundation.innerText = 'Dla fundacji "' + institution + '".';
    const addressLi1 = document.createElement("li");
    const addressLi2 = document.createElement("li");
    const addressLi3 = document.createElement("li");
    const addressLi4 = document.createElement("li");

    addressLi1.textContent = street;
    addressLi2.textContent = city;
    addressLi3.textContent = postCode;
    addressLi4.textContent = phone;
    addressUl.appendChild(addressLi1)
    addressUl.appendChild(addressLi2)
    addressUl.appendChild(addressLi3)
    addressUl.appendChild(addressLi4)

    const termLi1 = document.createElement("li");
    const termLi2 = document.createElement("li");
    const termLi3 = document.createElement("li");

    termLi1.textContent = date;
    termLi2.textContent = time;
    termLi3.textContent = pickUpComment;
    termUl.appendChild(termLi1);
    termUl.appendChild(termLi2);
    termUl.appendChild(termLi3);
})