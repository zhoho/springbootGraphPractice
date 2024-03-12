const ageDistribution = [[${ageDistribution}]]; // 연령 분포 데이터
const femalesByAgeGroup = [[${femalesByAgeGroup}]]; // 연령대별 여성 수 데이터
const totalPeople = Object.values(ageDistribution).reduce((a, b) => a + b, 0); // 총 인원 계산
const femaleCount = Object.values(femalesByAgeGroup).reduce((a, b) => a + b, 0); // 전체 여성 수 계산
const otherCount = totalPeople - femaleCount; // '기타' 성별 인원 계산

const mixedCtx = document.getElementById('mixedChart'); // 혼합 차트를 위한 캔버스 요소 선택

let mixedChart = new Chart(mixedCtx, {
    data: {
        datasets: [{
            type: 'bar',
            label: '연령 분포',
            data: Object.values(ageDistribution),
            borderColor: 'rgb(54, 162, 235)',
            backgroundColor: 'rgba(54, 162, 235, 0.5)'
        }, {
            type: 'line',
            label: '성별 분포 (여성)',
            data: Object.values(femalesByAgeGroup),
            borderColor: 'rgb(255, 99, 132)',
            fill: false
        }],
        labels: Object.keys(ageDistribution) // X축 레이블을 연령대별로 설정
    },
    options: {
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    stepSize: 1,
                    callback: function(value) {
                        if (value % 1 === 0) {
                            return value;
                        }
                    }
                }
            }
        }
    }
});


$(document).ready(function() {
    // 남성 버튼 클릭 이벤트
    $('#btnradio2').click(function() {
        $.ajax({
            url: '/index/male/age-distribution',
            type: 'GET',
            success: function(data) {
                mixedChart.data.datasets[1].data = Object.values(data); // 연령대별 남성 수 데이터로 업데이트
                mixedChart.data.datasets[1].label = '성별 분포 (남성)';
                mixedChart.update(); // 차트 업데이트
            },
            error: function(error) {
                console.log(error);
            }
        });
    });

    // 여성 버튼 클릭 이벤트
    $('#btnradio1').click(function() {
        // 초기에 설정된 여성 데이터를 사용하여 차트 업데이트
        mixedChart.data.datasets[1].data = Object.values(femalesByAgeGroup);
        mixedChart.data.datasets[1].label = '성별 분포 (여성)';
        mixedChart.update(); // 차트 업데이트
    });
});