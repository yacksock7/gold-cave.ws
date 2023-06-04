export function validateEmail(email) {
    const regExp = /^[a-zA-Z0-9]([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    return regExp.test(String(email).toLowerCase());
}

export function validateName(name) {
    // const regExp = /^\s{0}[가-힣]{2,30}\s{0}$|^\s{0}[a-zA-Z]{2,30}\s[a-zA-Z]{2,30}$\s{0}$/;
    const regExp = /^[가-힣a-zA-Z0-9][가-힣a-zA-Z0-9\s]{0,50}[가-힣a-zA-Z0-9]$/g;
    return regExp.test(String(name).toLowerCase());
}

export function validatePasswordCombination(password) {
    // const regExp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{10,}$/;
    // return regExp.test(String(password).toLowerCase());

    const num = password.search(/[0-9]/g);
    const eng = password.search(/[a-z]/gi);
    const spe = password.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if ((num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0)) {
        return false;
    }
    return true;
}

export function validatePasswordLength(password) {
    const pw = password;
    if (pw.length < 10) {
        return false;
    }
    return true;
}

export function checkSpace(e) {
    const space = /\s/;
    if (space.exec(e.target.value)) {
        e.target.value = e.target.value.replace(' ', '');
        return false;
    }
}

export function checkBirthday(e) {
    e.target.value = e.target.value.replace(/[^0-9]/g, '');
    e.target.value = e.target.value.replace(/(\d{4})(\d{2})(\d{2})/g, '$1-$2-$3');
    return false;
}

export function checkPhone(e) {
    e.target.value = e.target.value.replace(/[^0-9]/g, '');
    if (e.target.value.length === 11) {
        e.target.value = e.target.value.replace(/(\d{3})(\d{4})(\d{4})/g, '$1-$2-$3');
    } else {
        e.target.value = e.target.value.replace(/(\d{3})(\d{3})(\d{4})/g, '$1-$2-$3');
    }
    return false;
}