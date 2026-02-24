import { useEffect } from "react";
import { saveToken } from "../services/authService";

function OAuthSuccess() {
  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    saveToken(params.get("token"));
    window.location.href = "/";
  }, []);

  return <h3>Logging in...</h3>;
}

export default OAuthSuccess;
