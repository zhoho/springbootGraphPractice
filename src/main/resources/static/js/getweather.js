$(document).ready(function() {
    $('#getWeather').click(function() {
        var lat = $('#latitude').val();
        var lon = $('#longitude').val();
        var apiKey = 'cb8af9f3317bb3f9118d23f3cae2dd15';
        var url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${apiKey}&units=metric&lang=kr`;

        $.get(url, function(data) {
            var location = data.name;
            var temperature = Math.round(data.main.temp);
            var weatherDescription = data.weather[0].description;
            var iconCode = data.weather[0].icon;
            var iconUrl = "https://openweathermap.org/img/w/" + iconCode + ".png";
            var windSpeed = data.wind.speed;
            var windDirection = data.wind.deg;
            var humidity = data.main.humidity;
            var pressure = data.main.pressure;
            var clouds = data.clouds.all;
            var sunrise = new Date(data.sys.sunrise * 1000).toLocaleTimeString();
            var sunset = new Date(data.sys.sunset * 1000).toLocaleTimeString();

            console.log(data);

            $('#location').html('위치: ' + location);
            $('#weather-data').html('기온: ' + temperature + '°C, 상태: ' + weatherDescription +
                '<img src="' + iconUrl + '">' +
                ', 풍속: ' + windSpeed + ' m/s, 풍향: ' + windDirection + '°' +
                ', 습도: ' + humidity + '%' +
                ', 기압: ' + pressure + ' hPa' +
                ', 구름량: ' + clouds + '%' +
                ', 일출 시간: ' + sunrise + ', 일몰 시간: ' + sunset);
        }).fail(function() {
            $('#weather-data').html('날씨 정보를 불러올 수 없습니다.');
        });
    });
});