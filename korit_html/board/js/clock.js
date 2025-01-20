function clockRun() {
    const phoneClock = document.querySelector(".phone-clock")
    setInterval(() => { // 정한 시간마다 함수를 실행
        const now = new Date();
        const nowHours = now.getHours();
        const nowMinutes = now.getMinutes() < 10 ? `0${now.getMinutes()}` : now.getMinutes();
        const nowClockText = `${nowHours}:${nowMinutes}`;
        phoneClock.innerHTML = nowClockText;
    }, 1000);
}

clockRun();
