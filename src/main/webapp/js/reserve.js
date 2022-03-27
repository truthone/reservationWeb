window.addEventListener("load", function () {
	const displayInfoId = document.querySelector("meta[name= 'displayInfoId']").getAttribute("content");
	productInfoAjax(displayInfoId);
	checkingFormValidatation();
});

function productInfoAjax(displayInfoId) {
	const url = "/reservation/api/products/" + displayInfoId + "/reserve";
	var oReq = new XMLHttpRequest();

	oReq.addEventListener("load", function () {
		let json = JSON.parse(this.responseText);
		let productMainImageInfo = json.productMainImageInfo;
		let displayInfo = json.displayInfo;
		let displayImageInfo = json.displayImageInfo;
		let productPriceInfo = json.productPriceInfo;
		let productId = displayInfo[0].productId;

		templatingProductMainImage(productMainImageInfo, displayInfo);
		templatingProductPriceInfo(productPriceInfo);
		templatingIdDataForSubmit(displayInfoId, productId);
	});

	oReq.open("GET", url);
	oReq.send();
}

function templatingIdDataForSubmit(displayInfoId, productId) {

	let displayInfoIdInput = document.createElement('input');
	let productIdInput = document.createElement('input');

	displayInfoIdInput.setAttribute("type", "hidden");
	displayInfoIdInput.setAttribute("name", "displayInfoId");
	displayInfoIdInput.setAttribute("value", displayInfoId);

	productIdInput.setAttribute("type", "hidden");
	productIdInput.setAttribute("name", "productId");
	productIdInput.setAttribute("value", productId);

	document.querySelector(".form_horizontal").appendChild(displayInfoIdInput);
	document.querySelector(".form_horizontal").appendChild(productIdInput);

}

function checkingFormValidatation() {
	$(".warning_msg").click(function (evt) {
		evt.target.style.visibility = "hidden";
	});

	document.querySelector(".form_horizontal").addEventListener("click", function (evt) {
		evt.target.addEventListener("change", function () {
			const target = evt.target.className;
			let checkingValid = new checkValid(target);
		});
	});

	document.getElementById("chk3").addEventListener("change", function (evt) {

		let checkingValid = new checkValid();

	});

	document.querySelector("#reserveInfoForm").addEventListener("submit", function (evt) {
		evt.preventDefault();

		let checkingSubmitValid = new checkSubmitValid();

	});
}

function checkValid(target) {
	let nameValue = document.querySelector("[name='reservationName']").value;
	let emailValue = document.querySelector("[name='reservationEmail']").value;
	let telValue = document.querySelector("[name = 'reservationTel']").value;

	this.changeColorSubmitBtn(nameValue, emailValue, telValue, target);
}

checkValid.prototype = {
	telValidTest: (telValue, target) => {
		if (!(telValue === '') && (/01[01]-\d{3,4}-\d{4}/).test(telValue)) return true;
		else {
			if (target === 'tel') $("#warning_msg_tel").css("visibility", "visible");
			return false;
		}
	},

	emailValidTest: (emailValue, target) => {
		if (!(emailValue === '') && (/^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/).test(emailValue)) return true;
		else {
			if (target === 'email') $("#warning_msg_email").css("visibility", "visible");
			return false;
		}
	},

	changeColorSubmitBtn: function (nameValue, emailValue, telValue, target) {
		let emailValid = this.emailValidTest(emailValue, target);
		let telValid = this.telValidTest(telValue, target);
		let agreementValid = $("input:checkbox[id='chk3']").prop("checked");

		if (agreementValid && emailValid && telValid && !(nameValue === '')) {
			$(".bk_btn_wrap").removeClass("disable");
		} else {
			$(".bk_btn_wrap").addClass("disable");
		}
	}
}

function checkSubmitValid() {
	let nameValue = document.querySelector("[name='reservationName']").value;
	let emailValue = document.querySelector("[name='reservationEmail']").value;
	let telValue = document.querySelector("[name = 'reservationTel']").value;
	let nowTotalCount = countTotalticketsAmount();
	let agreementValid = $("input:checkbox[id='chk3']").prop("checked");
	this.checkingAllCondition(nameValue, emailValue, telValue, nowTotalCount, agreementValid);

}

checkSubmitValid.prototype = {

	telValidTest: (telValue) => {
		if (!(telValue === '') && (/01[01]-\d{3,4}-\d{4}/).test(telValue)) return true;
		else return false;
	},

	emailValidTest: (emailValue) => {
		if (!(emailValue === '') && (/^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/).test(emailValue)) return true;
		else return false;
	},

	checkingAllCondition: function (nameValue, emailValue, telValue, nowTotalCount, agreementValid) {
		let emailValid = this.emailValidTest(emailValue);
		let telValid = this.telValidTest(telValue);

		if ((nameValue == '') || !emailValid || !telValid || !agreementValid || nowTotalCount === 0) {

			if (!emailValid) $("#warning_msg_email").css("visibility", "visible");
			else if (!telValid) $("#warning_msg_tel").css("visibility", "visible");
			else if (nameValue == '') $("#warning_msg_name").css("visibility", "visible");
			else if (nowTotalCount === 0) window.alert('수량을 선택해주세요');

		} else {
			let form = document.querySelector("#reserveInfoForm");
			addPriceInfo();
			form.submit();
			form.reset();
		}
	}

}



function templatingProductMainImage(productMainImageInfo, displayInfo) {
	const productMainImage = document.querySelector(".img_thumb");
	const mainImageSaveFileName = productMainImageInfo[0].saveFileName;
	productMainImage.src = '../../' + mainImageSaveFileName;
}


const priceType = {
	A: "성인",
	Y: "청소년",
	B: "유아",
	S: "셋트",
	D: "장애인",
	C: "지역주민",
	E: "얼리버드",
	V: "VIP",
	R: "R석",
	B: "B석 ",
	S: "S석",

	priceTypeToString: (priceTypeMark) => priceType[priceTypeMark]
}


function templatingProductPriceInfo(productPriceInfo) {
	const Section_ticketQuantity = document.querySelector("#Section_ticketQuantity").innerHTML;
	let resultHTML = "";
	let bindTemplate = Handlebars.compile(Section_ticketQuantity);

	if (0 != productPriceInfo.length) {
		productPriceInfo.forEach(function (productPriceInfo, index) {
			productPriceInfo.priceStringType = priceType.priceTypeToString(productPriceInfo.priceTypeName);
			resultHTML += bindTemplate(productPriceInfo);
		});

		//		p = productPriceInfo.map(index => priceType.priceTypeToString(index.priceTypeName));

		//			resultHTML += bindTemplate(p);

	}

	document.querySelector(".ticket_body").insertAdjacentHTML("beforeend", resultHTML);
	const qtyTabs = $(".ticket_body > .qty");
	let countTabsArray = [];

	for (let i = 0; i < qtyTabs.length; i++) {

		countTabsArray.push(new countControlTap(qtyTabs[i]));
	}

	countTotalticketsAmount(countTabsArray);
}

function countTotalticketsAmount() {
	let countControlInput = $(".count_control_input");
	let totalCount = 0;

	for (i = 0; i < countControlInput.length; i++) {

		let count = Number(countControlInput[i].value);
		totalCount += count;
	}

	$("#totalCount").text(totalCount);

	return totalCount;
}

function countControlTap(qtyTab) {
	this.tabElement = qtyTab;
	this.registerCalcuateFunction(this.tabElement);
}

countControlTap.prototype = {

	registerCalcuateFunction: function (qtyTab) {
		const countInput = qtyTab.querySelector(".count_control_input");
		const minusBtn = qtyTab.querySelector(".btn_plus_minus.spr_book2.ico_minus3");
		const plusBtn = qtyTab.querySelector(".btn_plus_minus.spr_book2.ico_plus3");
		const individualPrice = qtyTab.querySelector(".individual_price");
		let nowValue = 0;
		const LIMIT = 10;
		const productPrice = qtyTab.querySelector(".price").innerHTML;

		minusBtn.addEventListener("click", function () {
			if (nowValue != 0) {
				nowValue--;
				countInput.value = nowValue;
				qtyTab.querySelector(".total_price").innerHTML = productPrice * nowValue;
				countTotalticketsAmount();

				if (nowValue === 0) {
					minusBtn.classList.add("disabled");
					countInput.classList.add("disabled");
					individualPrice.classList.remove("on_color");
				}
			}
		});

		plusBtn.addEventListener("click", function () {
			if (nowValue != LIMIT) {
				nowValue++;
				countInput.value = nowValue;
				qtyTab.querySelector(".total_price").innerHTML = productPrice * nowValue;
				countTotalticketsAmount();
				individualPrice.classList.add("on_color");

				if (nowValue !== LIMIT) {
					minusBtn.classList.remove("disabled");
					countInput.classList.remove("disabled");
				} else if (nowValue === LIMIT) plusBtn.classList.add("disabled");
			}
		});
	}
}

function addPriceInfo() {
	const priceTabs = document.getElementsByClassName("individual_price on_color");


	for (let i = 0; i < priceTabs.length; i++) {
		const countValue = priceTabs[i].previousElementSibling.children[1].value;
		const priceId = priceTabs[i].parentElement.parentElement.id;

		let reservationPriceIdInput = document.createElement('input');
		let reservationPriceCountInput = document.createElement('input');

		reservationPriceIdInput.setAttribute("type", "hidden");
		reservationPriceIdInput.setAttribute("name", "reservationInfoPrices[" + i + "]" + ".productPriceId");
		reservationPriceIdInput.setAttribute("value", priceId);

		reservationPriceCountInput.setAttribute("type", "hidden");
		reservationPriceCountInput.setAttribute("name", "reservationInfoPrices[" + i + "]" + ".count");
		reservationPriceCountInput.setAttribute("value", countValue);

		document.querySelector(".form_horizontal").appendChild(reservationPriceIdInput);
		document.querySelector(".form_horizontal").appendChild(reservationPriceCountInput);

	}

}