export function saveToken(token) {
  localStorage.setItem("token", token);
}

export function getToken() {
  return localStorage.getItem("token");
}

export function logout() {
  localStorage.removeItem("token");
  window.location.href = "/login";
}

export function loginWithGoogle() {
  window.location.href =
    "http://localhost:8080/oauth2/authorization/google";
}
