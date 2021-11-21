$(function() {
	var usernameCookie = getCookie("username");
	var role = getCookie("role");

	console.log(role);

	if (!usernameCookie) {
		$("#navbarSupportedContent > .navbar-nav").hide();
		$("#login-btn").show();
		$("#logout-btn").hide();
		console.log(3);
		if (role == 'MANAGER' || role == 'EMPLOYEE' || role == 'ADMIN') {
			console.log(1);
			$("#manager-controller").show();
		}
		else {
			console.log(2);
			$("#manager-controller").hide();
		}
	} else {
		$("#navbarSupportedContent > .navbar-nav").show();
		$("#login-btn").hide();
		$("#logout-btn").show();
	}
});

$(function() {
	var role = getCookie("role");

	console.log(role);
	console.log(3);
	if (role == 'MANAGER' || role == 'EMPLOYEE' || role == 'ADMIN') {
		console.log(1);
		$("#manager-controller").show();
	}
	else {
		console.log(2);
		$("#manager-controller").hide();
	}
});

function getCookie(key) {
	let name = key + "=";
	let decodedCookie = decodeURIComponent(document.cookie);
	let ca = decodedCookie.split(';');
	for (let i = 0; i < ca.length; i++) {
		let c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

function clickNavHome() {
	$(".main").load("jsp/home.jsp");
}

function clickNavBook() {
	location.replace("./book")
}

function clickNavElectronic() {
	location.replace("./electronic")
}

function clickNavClothes() {
	location.replace("./clothes")
}

function clickNavShoes() {
	location.replace("./shoes")
}

function clickNavCart() {
	location.replace("./cart")
}

function clickNavOrder() {
	location.replace("./order")
}

function clickNavAccount() {
	location.replace("./account")
}

function clickNavLogin() {
	location.replace("./login")
}

function clickNavLogout() {
	location.replace("./logout");
}

function clickNavViewListGroups() {
	$(".main").load("jsp/group.jsp");
}
