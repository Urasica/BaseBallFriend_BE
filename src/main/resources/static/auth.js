document.addEventListener('DOMContentLoaded', function() {
    const nickname= document.getElementById('auth-nickname');
    const authSection = document.getElementById('auth-section');
    const token = localStorage.getItem('token');
    const username = localStorage.getItem('username');

    if (token && username) {
        // 로그인 상태일 때
        authSection.innerHTML = `
            <button class="btn btn-outline-warning auth-button auth-button-logout" id="logoutButton">로그아웃</button>
        `;

        nickname.innerHTML = `어서오세요 <span class="nav-link auth-button">${username} 님</span>`;

        document.getElementById('logoutButton').addEventListener('click', function() {
            localStorage.removeItem('token');
            localStorage.removeItem('username');
            window.location.reload(); // 페이지를 새로고침하여 로그아웃 반영
        });
    } else {
        // 로그인하지 않았을 때
        authSection.innerHTML = `
            &nbsp<a class="btn btn-outline-success" href="/login">로그인</a>
        `;
    }
});