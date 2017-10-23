// $(function(){
//     $weight = $('input[name="weight"]');
//
//     $weight.change (
//         function() {
//             $.ajax({
//                 type: "GET",
//                 url: "getSuitableTrucks",
//                 data: {"weight": $weight.val() },
//                 dataType: 'json',
//                 success: function(data){
//                     $('select[name="truck"]')
//                         .empty()
//                         .append('<option selected="selected" value="">Select truck</option>')
//                     if(data.length !==0){
//                     $.each(data, function(index, value) {
//                         $('select[name="truck"]')
//                             .append($('<option>').text(value).val(value))
//                         ;
//                     });}else {alert("No available trucks for this order!")}
//                 }
//             });
//         }
//     );
// });