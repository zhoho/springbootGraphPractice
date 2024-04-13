//
// function getWeather(gridX, gridY) {
//     console.log("Fetching weather for coordinates:", gridX, gridY); // Debugging log
//     var xhr = new XMLHttpRequest();
//     var url = 'https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst';
//     var queryParams = '?' + encodeURIComponent('serviceKey') + '=' + 'hogAXADhx1TwUFYu9kBfwwvsv5m%2FwsLzvQT2tBT0BFSH3eLnXp88Dx%2FR6x2GCPtWKfaUQEPix2VcWYEp0Hz5Tw%3D%3D';
//     queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1');
//     queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('50');
//     queryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('XML');
//     queryParams += '&' + encodeURIComponent('base_date') + '=' + encodeURIComponent('20240413');
//     queryParams += '&' + encodeURIComponent('base_time') + '=' + encodeURIComponent('0500');
//     queryParams += '&' + encodeURIComponent('nx') + '=' + encodeURIComponent(gridX);
//     queryParams += '&' + encodeURIComponent('ny') + '=' + encodeURIComponent(gridY);
//     xhr.open('GET', url + queryParams);
//     xhr.onreadystatechange = function () {
//         if (this.readyState === 4 && this.status === 200) {
//             // 날씨 데이터 처리
//             displayWeatherData(this.responseXML);
//             // console.log(this.responseText);
//         }
//     };
//     xhr.send();
// }
//
// function displayWeatherData(xml) {
//     const items = xml.getElementsByTagName("item");
//     let weatherData = {};
//
//     // 데이터 구조화
//     for (let i = 0; i < items.length; i++) {
//         const category = items[i].getElementsByTagName("category")[0].childNodes[0].nodeValue;
//         const value = items[i].getElementsByTagName("fcstValue")[0].childNodes[0].nodeValue;
//         const time = items[i].getElementsByTagName("fcstTime")[0].childNodes[0].nodeValue;
//
//         if (!weatherData[time]) {
//             weatherData[time] = {};
//         }
//         weatherData[time][category] = value;
//     }
//
//     // 테이블 생성
//     let output = "<h3>Weather Forecast</h3>";
//     output += "<table class='table table-striped table-hover'><thead class='table-dark'><tr><th>Time</th><th>Temperature (°C)</th><th>Wind Speed (m/s)</th><th>Humidity (%)</th><th>Precipitation</th><th>Sky Condition</th></tr></thead><tbody>";
//
//     for (const [time, data] of Object.entries(weatherData)) {
//         output += `<tr>
//                     <td>${time}</td>
//                     <td>${data['TMP'] || '--'}</td>
//                     <td>${data['WSD'] || '--'}</td>
//                     <td>${data['REH'] || '--'}</td>
//                     <td>${data['PCP'] || '--'}</td>
//                     <td>${data['SKY'] || '--'}</td>
//                   </tr>`;
//     }
//
//     output += "</tbody></table>";
//     document.getElementById("weather-data").innerHTML = output;
// }
//
//
// $(document).ready(function() {
//     setupAutocomplete('#city', 'city', 'cityResults');
//     setupAutocomplete('#district', 'district', 'districtResults');
//     setupAutocomplete('#subdistrict', 'subdistrict', 'subdistrictResults');
//
//     function setupAutocomplete(selector, type, resultListId) {
//         $(selector).on('input', function() {
//             var input = $(this).val();
//             if (input.length > 1) {
//                 $.ajax({
//                     url: '/searchAddress',
//                     method: 'GET',
//                     data: { type: type, searchTerm: input },
//                     success: function(data) {
//                         var autocompleteResults = $('#' + resultListId);
//                         autocompleteResults.empty();
//                         data.forEach(function(item) {
//                             autocompleteResults.append($('<option>').val(item));
//                         });
//                     }
//                 });
//             }
//         });
//     }
// });
