var count = 0;
var phoneOrPhonehide = function(){
    // $("#accountlogin").html("")
    if(count == 0){
        $("#phonelogin").hide();
        $("#accountlogin").show();
        $("#loginAccountOrPhone").html("手机登录");
        count = 1;
    }else if(count == 1){
        $("#accountlogin").hide();
        $("#phonelogin").show();
        $("#loginAccountOrPhone").html("账号登录");
        count = 0;
    }
}