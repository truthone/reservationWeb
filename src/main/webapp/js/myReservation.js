window.addEventListener("load", function () {
	reservationListAjax();
});

function reservationListAjax() {
	let reservationEmail = document.querySelector(".reservationEmail").innerText;
	let oReq = new XMLHttpRequest();
	const url = "api/reservations?reservationEmail=" + reservationEmail;

	oReq.addEventListener("load", function () {
		let json = JSON.parse(this.responseText);
		let reservationList = json.reservation;
		registerHelperForhandlebars();
		templatingReservationInfo(reservationList);
	});

	oReq.open("GET", url);
	oReq.send();
}

templating = {
	bindingTemplate: function (bindingPlace, bindingData) {
		let template = Handlebars.compile(bindingPlace);
		return template(bindingData);
	},
	insertResultHTML: function (insertList, resultHTML) {
		insertList.insertAdjacentHTML("beforeend", resultHTML);
	}
}

function templatingReservationInfo(reservationList) {
	const cardItemTemplate = document.querySelector("#cardTemplate").innerHTML;
	const canceledCardItemTemplate = document.querySelector("#canceledCardTemplate").innerHTML;
	const usedCardItemTemplate = document.querySelector("#usedCardTemplate").innerHTML;

	let cardList = document.querySelector(".card.confirmed");
	let usedList = document.querySelector(".card.used");
	let canceledList = document.querySelector(".card.used.cancel");
	let resultHTML = "";
	let resultHTMLForCancel = "";
	let resultHTMLForUsed = "";


	if (reservationList.length === 0) {
		addEmptyList(cardList);
		addEmptyList(canceledList);
		addEmptyList(usedList);
	} else {
		let cancelItemCount = 0;
		let itemCount = 0;

		mappingPriceInfoTypeForKOR(reservationList);

		reservationList.forEach(function (info) {
			if (info.reservationInfo.cancelFlag == 0) {
				itemCount++;
				resultHTML += templating.bindingTemplate(cardItemTemplate, info);
				resultHTMLForUsed += templating.bindingTemplate(usedCardItemTemplate, info);
			} else if (info.reservationInfo.cancelFlag != 0) {
				cancelItemCount++;
				resultHTMLForCancel += templating.bindingTemplate(canceledCardItemTemplate, info);
			}
		});

		if (itemCount != 0) {
			templating.insertResultHTML(cardList, resultHTML);
			templating.insertResultHTML(usedList, resultHTMLForUsed);
		} else if (itemCount == 0) {
			addEmptyList(cardList);
		}
		if (cancelItemCount != 0) {
			templating.insertResultHTML(canceledList, resultHTMLForCancel);
		} else if (cancelItemCount == 0) {
			addEmptyList(canceledList);
		}
	}
	clickReservationCancelBtn();
}


function registerHelperForhandlebars() {
	Handlebars.registerHelper('countTotal', function (reservationInfoPrice) {
		let totalCount = 0;
		reservationInfoPrice.forEach(index => totalCount += index.count)
		return totalCount;
	});

	Handlebars.registerHelper('caculateTotalPrice', function (reservationInfoPrice) {
		let totalPrice = 0;
		reservationInfoPrice.forEach(index => totalPrice += index.price * index.count)
		return totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	});
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

function mappingPriceInfoTypeForKOR(reservationList) {
	//	if(reservationList.length === 0){
	//		addEmptyList();
	//	}else{
	//		reservationList.forEach(function(reservationListIndex){
	//			reservationListIndex.reservationInfoPrice.forEach(function(priceInfoIndex){
	//				const typeNameKOR = priceTypeToString((priceInfoIndex.priceTypeName));				 
	//			});
	//			
	//		let mappingTypeList = reservationList.map(function(index){
	//			index.});
	//		}
	//				index => priceTypeToString(index.priceTypeName));
	//		return mappingTypeList;
	//	}
}


function clickReservationCancelBtn() {
	let confirmedList = document.querySelector(".card.confirmed");

	confirmedList.addEventListener("click", function (evt) {
		if (evt.target.className == 'btn') {
			let clickedReservationItem = evt.target.parentElement.parentElement;
			let confirmedList = document.querySelector(".card.confirmed");
			let clickedBookingNumber = parseInt(clickedReservationItem.querySelector("#bookingNumber").innerText);
			let clickedItemTitle = clickedReservationItem.querySelector(".tit").innerText;
			let clickedItemPeriod = clickedReservationItem.querySelector("#period").innerText;
			let cancelElement = evt.target.parentElement;

			clickCancelPopupBtn(cancelElement, clickedItemTitle, clickedItemPeriod, clickedBookingNumber);
			clickClosePopUpBtn();

		} else if (evt.target.parentElement.className == 'btn') {
			let clickedReservationItem = evt.target.parentElement.parentElement.parentElement;
			let confirmedList = document.querySelector(".card.confirmed");
			let clickedBookingNumber = parseInt(clickedReservationItem.querySelector("#bookingNumber").innerText);
			let clickedItemTitle = clickedReservationItem.querySelector(".tit").innerText;
			let clickedItemPeriod = clickedReservationItem.querySelector("#period").innerText;
			let cancelElement = evt.target.parentElement.parentElement;

			clickCancelPopupBtn(clickedItemTitle, clickedItemPeriod, clickedBookingNumber);
			clickClosePopUpBtn();
		}
	});

}

function clickCancelPopupBtn(title, period, reservationId) {
	let cancelPopup = document.querySelector(".popup_booking_wrapper");
	cancelPopup.style.display = "block";
	$("#popTitle").text(title);
	$("#popPeriod").text(period);
	let cancelList = document.querySelector(".card.used.cancel");

	cancelPopup.querySelector(".pop_bottom_btnarea").addEventListener("click", evt => {

		if ((evt.target.parentElement.id || evt.target.parentElement.parentElement.id) === "cancel_pop_noBtn") {
			cancelPopup.style.display = "none";
		} else if ((evt.target.parentElement.id || evt.target.parentElement.parentElement.id) === "cancel_pop_yesBtn") {
			cancelAjax(reservationId, cancelPopup);
		}
	});
}

function cancelAjax(reservationId, cancelPopup) {
	let oReq = new XMLHttpRequest();
	let url = "/reservation/api/reservations/" + reservationId;
	let isSuccess = 'false';
	oReq.addEventListener("load", function () {
		let json = JSON.parse(this.responseText);
		isSuccess = json.success;
		cancelSuccess(isSuccess, cancelPopup);
	});
	oReq.open("PUT", url);
	oReq.send();

}

function cancelSuccess(isSuccess, cancelPopup) {
	let cancelList = document.querySelector(".card.used.cancel");
	if (isSuccess !== 'false') {
		cancelPopup.style.display = "none";
		location.reload(true);
		addEmptyList(cancelElement.offsetParent.offsetParent);
	} else {
		cancelPopup.style.display = "none";
		window.alert('취소되지 않았습니다!');
	}
}

function addEmptyList(list) {
	debugger;
	if (list.childElementCount === 1) {
		const noList = document.querySelector("#noList");
		list.insertAdjacentHTML("beforeend", noList.innerHTML);
	}
}

function clickClosePopUpBtn() {
	let cancelPopup = document.querySelector(".popup_booking_wrapper");
	document.querySelector(".popup_btn_close").addEventListener("click", function () {
		cancelPopup.style.display = "none";
	});
}