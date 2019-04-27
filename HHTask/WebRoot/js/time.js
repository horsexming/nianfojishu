	window.onload = function() {
	function showTime() {
		var datetime = new Date();
		var h = datetime.getHours();
		var m = datetime.getMinutes();
		var s = datetime.getSeconds();

		if (h < 10) {
			h = "0" + h;
		}
		if (m < 10) {
			m = "0" + m;
		}
		if (s < 10) {
			s = "0" + s;
		}

		var hour = document.getElementById("hour");
		var minute = document.getElementById("minute");
		var seconds = document.getElementById("second");

		hour.innerHTML = h;
		minute.innerHTML = m;
		seconds.innerHTML = s;
		setTimeout(showTime, 1000);
	}
	showTime();

}
