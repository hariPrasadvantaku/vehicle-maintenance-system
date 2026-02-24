import api from "../api/axiosConfig";

function Signup() {
  function handleSubmit(e) {
    e.preventDefault();
    const data = {
      name: e.target.name.value,
      email: e.target.email.value,
      password: e.target.password.value
    };

    api.post("/auth/signup", data)
       .then(() => alert("Signup success"));
  }

  return (
    <form onSubmit={handleSubmit}>
      <input name="name" />
      <input name="email" />
      <input name="password" type="password"/>
      <button>Signup</button>
    </form>
  );
}

export default Signup;
