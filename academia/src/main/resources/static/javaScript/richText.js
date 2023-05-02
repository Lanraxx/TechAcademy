var editor = document.getElementById('editor');
var description = document.getElementById('description');

editor.addEventListener('input', function() {
    description.value = editor.innerHTML;
});


///////////////////////////////////////////



$('.toolbar a').click(function(e) {
var command = $(this).data('command');
if (command == 'h1' || command == 'h2' || command == 'p') {

  document.execCommand('formatBlock', false, command);
}
if (command == 'createlink' || command == 'insertimage') {
  url = prompt('Enter the link here: ', 'http://');
  document.execCommand($(this).data('command'), false, url);
} else document.execCommand($(this).data('command'), false, null);
});