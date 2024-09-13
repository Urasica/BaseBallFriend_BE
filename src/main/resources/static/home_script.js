document.addEventListener('DOMContentLoaded', function() {
    const apiUrl = '/api/schedule';
    let currentDate = new Date();
    let scheduleData = [];

    function formatDate(date) {
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        return `${month}.${day}(${['일', '월', '화', '수', '목', '금', '토'][date.getDay()]})`;
    }

    function loadSchedule(date) {
        const formattedDate = formatDate(date);

        if (scheduleData.length === 0) {
            fetch(apiUrl)
                .then(response => response.json())
                .then(data => {
                    scheduleData = data;
                    displaySchedule(date);
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        } else {
            displaySchedule(date);
        }
    }

    function displaySchedule(date) {
        const formattedDate = formatDate(date);
        const filteredGames = scheduleData.filter(game => game.date === formattedDate);

        const table = `
      <table class="table">
        <thead>
          <tr>
            <th>시간</th>
            <th>원정팀</th>
            <th>홈팀 점수</th>
            <th>원정팀 점수</th>
            <th>홈팀</th>
            <th>장소</th>
            <th>비고</th>
          </tr>
        </thead>
        <tbody>
          ${filteredGames.length > 0 ? filteredGames.map(game => `
            <tr>
              <td>${game.time}</td>
              <td class="team">
                <img src="/image/${game.team1}.png" class="team-logo" alt="${game.team1} 로고">
                ${game.team1}
              </td>
              <td>${game.team1Score || 'N/A'}</td>
              <td>${game.team2Score || 'N/A'}</td>
              <td class="team">
                <img src="/image/${game.team2}.png" class="team-logo" alt="${game.team2} 로고">
                ${game.team2}
              </td>
              <td>${game.place || 'N/A'}</td>
              <td style="font-size: small">${game.note || 'N/A'}</td>
            </tr>
          `).join('') : '<tr><td colspan="7" class="text-center">오늘의 경기가 없습니다.</td></tr>'}
        </tbody>
      </table>
      <span style="color: #aaaaaa; text-align: center">경기를 클릭하여 문자중계를 확인할 수 있습니다.</span>
    `;

        document.getElementById('schedule-table').innerHTML = table;
        document.getElementById('current-date').textContent = formattedDate;
    }

    document.getElementById('prev-day').addEventListener('click', function() {
        currentDate.setDate(currentDate.getDate() - 1);
        loadSchedule(currentDate);
    });

    document.getElementById('next-day').addEventListener('click', function() {
        currentDate.setDate(currentDate.getDate() + 1);
        loadSchedule(currentDate);
    });

    loadSchedule(currentDate); // 초기 로드
});