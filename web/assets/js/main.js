// preloader
$(window).on("load", function() {
    $("#overlay").fadeOut(), $("#preloader").delay(350).fadeOut("slow"), $("body").delay(350).css({
        overflow: "visible"
    })
});

//smooth link
$(function() {
    $(".smooth").click(function() {
        if (location.pathname.replace(/^\//, "") == this.pathname.replace(/^\//, "") && location.hostname == this.hostname) {
            var a = $(this.hash);
            if (a = a.length ? a : $("[name=" + this.hash.slice(1) + "]"), a.length) return $("html,body").animate({
                scrollTop: a.offset().top
            }, 700), !1
        }
    })
})


$(document).ready(function(){
    // menu filter
   $('.filter a').on('click', function(event){
       event.preventDefault();
       $('.filter a').removeClass('active');  
       $(this).addClass("active");
       var currentId = $(this).attr('href');
       $(".card").fadeOut(300);
       $(currentId).delay(300).fadeIn();
   });
    
    //show arrows
    $('.evnt-calendar').on('mouseenter', function(){
        if ($(".arrows").hasClass('show') == false){
            $(".arrows").addClass('show');
        } else {
            return false;
        }
    });
    $('.evnt-calendar').on('mouseleave', function(){
        if ($(".arrows").hasClass('show') == true){
            $(".arrows").removeClass('show');
        } else {
            return false;
        }
    });
    
    //handlers
    $('.handlers a').on('click', function(event){
       event.preventDefault();
       $('.handlers a').removeClass("active");
       $(this).addClass("active");
       var place = $(this).parent().index();
       $('.scroll').animate({'margin-left':'-' + 100 * place + "%"}, 'slow');
      });
    
    //next
    $(".arrows #next").on('click', function(event){
        event.preventDefault();
        if ($('.handlers .active').parent().index() <= $('.handlers .active').parent().length){
            $('.handlers .active').parent().next().children('a').trigger('click', event.preventDefault());
        } else {
            $('.handlers li').first().children('a').trigger('click', event.preventDefault());
        }
    });
    
    //prev
    $(".arrows #prev").on('click', function(event){
        event.preventDefault();
        if ($('.handlers .active').parent().index() > 0){
            $('.handlers .active').parent().prev().children('a').trigger('click', event.preventDefault());
        } else {
            $('.handlers li').last().children('a').trigger('click', event.preventDefault());
        }
    });
});

