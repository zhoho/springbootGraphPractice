// script.js
function updateTime() {
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');

    const timeString = `${hours}:${minutes}`;
    document.getElementById('clock').textContent = timeString;
}

setInterval(updateTime, 1000); // 매 초마다 updateTime 함수 실행
