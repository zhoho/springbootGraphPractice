$(document).ready(function() {
    $('#getWeather').click(function() {
        var lat = $('#latitude').val();
        var lon = $('#longitude').val();
        var apiKey = 'cb8af9f3317bb3f9118d23f3cae2dd15';
        var url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${apiKey}&units=metric&lang=kr`;

        $.get(url, function(data) {
            var location = data.name
            var temperature = data.main.temp;
            var weatherDescription = data.weather[0].description;
            var iconCode = data.weather[0].icon;
            var iconUrl = "https://openweathermap.org/img/w/" + iconCode + ".png";

            $('#location').html('위치: ' + location);
            $('#weather-data').html('기온: ' + temperature + '°C, ' + '상태: ' + weatherDescription + '<img src="' + iconUrl + '">');
        }).fail(function() {
            $('#weather-data').html('날씨 정보를 불러올 수 없습니다.');
        });
    });
});