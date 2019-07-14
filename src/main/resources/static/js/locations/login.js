$(document).ready(function(){
    $("#but_submit").click(function(){
        var username = $("#username").val().trim();
        var password = $("#password").val().trim();

        if( username != "" && password != "" ){
            $.ajax({
                url:'http://localhost:8080/login',
                type:'post',
                data:{username:username,password:password},
                success:function(response){
                    var msg = "";
                    console(response+'  here we get');
                    if(response == 1){
                        window.location = "home.php";
                    }else{
                        msg = "Invalid username and password!";
                    }
                    $("#message").html(msg);
                }
            });
        }
    });
});