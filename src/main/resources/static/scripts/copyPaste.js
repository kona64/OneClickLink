 function copyTextBox() {
  var copyText = document.getElementById("linkBox");
  copyText.select();
  copyText.setSelectionRange(0, 99999);
  navigator.clipboard.writeText(copyText.value);
} 