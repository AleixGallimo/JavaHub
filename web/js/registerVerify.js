
function verify() {
    var username = document.getElementById("username");
    var password = document.getElementById("password");
    var rePassword = document.getElementById("rePassword");

    var e1 = document.getElementById("e1");
    var e2 = document.getElementById("e2");
    var e3 = document.getElementById("e3");

    var d1 = document.getElementById("d1");
    var d2 = document.getElementById("d2");
    var d3 = document.getElementById("d3");

    var email = document.getElementById("email");
    var phone = document.getElementById("phone");
    var span = document.getElementsByTagName("span");

    //用户名由英文字母和数字组成的4-16位字符，以字母开头
    var uCheck = /^[a-zA-Z][a-zA-Z0-9]{3,15}$/;
    if (!uCheck.test(username.value) || username.value == ""){
        span[0].style.color = "red";
        return false;
    }

    //密码由英文字母和数字组成的4-16位字符
    var pCheck = /^[a-zA-Z0-9]{4,16}$/;
    if (!pCheck.test(password.value) || password.value == ""){
        span[1].style.color = "red";
        return false;
    }

    //校验重新输入密码是否一致
    if (password.value != rePassword.value || rePassword.value == ""){
        span[2].innerText = "两次密码不一致";
        span[2].style.color = "red";
        return false;
    }

    //校验hobbies三选一，不能全为空
    var count = 0;
    if (e1.value != ""){
        count++;
    } else if (e2.value != ""){
        count++;
    } else if (e3.value != ""){
        count++;
    }
    if (count == 0){
        span[3].style.color = "red";
        return false;
    }

    //email格式
    var eCheck = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    if (!eCheck.test(email.value) || email.value == ""){
        span[4].style.color = "red";
        return false;
    }

    //1开头，11位数字
    var phoneCheck = /^1\d{10}$/;
    if (!phoneCheck.test(phone.value) || phone.value == ""){
        span[5].style.color = "red";
        return false;
    }

    //校验address地址是否为默认
    count = 3;
    if (d1.value == "default"){
        count --;
    } else if (d2.value == "default"){
        count --;
    } else if (d3.value == "default"){
        count --;
    }
    if (count != 3){
        span[7].style.color = "red";
        return false;
    }
    return true;

}