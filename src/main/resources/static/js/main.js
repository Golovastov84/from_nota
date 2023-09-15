$(function(){


    $('#enterId').click(function(){
                var incomingText = document.getElementById('incomingTextId').value;
               /* if(incomingText == ""){
                            alert("Введите текст");
                        } else {*/
                            $.ajax({
                                method: "POST",
                                url: '/inTexts',
                                data: incomingText,
                                success: function(response){
                                    document.getElementById('calculationResultId').value = response;
                                        $('#choiceLogin').css({display: 'none'});
                                        $('#registrationWindow').css({display: 'flex'});
                                }
                            });
                       /* }

                         $('#choiceLogin').css({display: 'none'});
                         $('#registrationWindow').css({display: 'flex'});*/
        return false;
    });

    $('.goHome').click(function(){
        document.getElementById('incomingTextId').value = "";
        document.getElementById('calculationResultId').value = "";
        $(objectPage).css({display: 'none'});
        $('#choiceLogin').css({display: 'flex'});
        return false;
    });
});



