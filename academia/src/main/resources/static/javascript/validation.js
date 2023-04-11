function validation() {
  var email = document.getElementsByName("email")[0].value;
  var password = document.getElementsByName("password")[0].value;
  if (!email.includes("@")) {
    alert("Correo electrónico inválido");
    return false;
  }
  if (password.length < 6) {
    alert("La contraseña debe tener al menos 6 caracteres");
    return false;
  }
  return true;
}
