document.querySelector('#signForm').addEventListener('submit', (e) => {
    e.preventDefault();

    const email = document.querySelector(`#email`)
    const password = document.querySelector(`#password`)
    const username = document.querySelector(`#username`)

    fetch(`/api/signup`, {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify({
            email : email.value,
            password : password.value,
            username : username.value
        })
    })
        .then((resp) => {
            if(!resp.ok && resp.status !== 400){
                throw new Error("회원 등록 오류" + resp.status);
            }
            return resp.json();
        })
        .then(data => {
            if (data.error) {
                alert("오류 발생" + data.error)
            } else {
                alert("등록 성공")
            }
        })
        .catch((error) => {
            console.error("fetch 에러" + error)
        });
    location.href="/login"
})