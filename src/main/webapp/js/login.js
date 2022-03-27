window.addEventListener("load", function () {
	checkingEmailValidataion();
	login();
});

function checkingEmailValidataion() {

	document.querySelector("#resrv_id").addEventListener("click", function (evt) {
		evt.target.addEventListener("change", function () {
			const emailValue = evt.target.value;
			checkingValidation.checkEmailValidation(emailValue);
		});
	});
}

var checkingValidation = {

	checkEmailValidation: function (emailValue) {
		if (!(emailValue === '') && (/^[\w+_]\w+@\w+\.\w+$/).test(emailValue)) return true;
		else {
			window.alert('이메일 양식이 맞지 않습니다! \n올바르게 입력해주세요');
			return false;
		}
	}
}

function login() {
	document.querySelector("#form1").addEventListener("submit", function (evt) {

		const emailValue = evt.target[0].form[0].value;

		evt.preventDefault();
		if (checkingValidation.checkEmailValidation(emailValue)) {
			evt.currentTarget.submit();
		} else {
			window.alert('이메일 양식이 맞지 않습니다! \n올바르게 입력해주세요');
		}
	});
}