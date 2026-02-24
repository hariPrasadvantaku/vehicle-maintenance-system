import api from "../api/axiosConfig";
import { saveToken, loginWithGoogle } from "../services/authService";

function Login() {

  function loginForm(e) {
    e.preventDefault();

    api.post("/auth/login", {
      email: e.target.email.value,
      password: e.target.password.value
    }).then(res => {
      saveToken(res.data.token);
      window.location.href = "/";
    });
  }

  return (
    <div>
      <form onSubmit={loginForm}>
        <input name="email"/>
        <input name="password" type="password"/>
        <button>Login</button>
      </form>

      <button onClick={loginWithGoogle}>
        Login with Google
      </button>
    </div>
  );
}

export default Login;
