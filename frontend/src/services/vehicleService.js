import api from "../api/axiosConfig";

export const getVehicles = () => api.get("/vehicle");

export const addVehicle = (data) => api.post("/vehicle", data);

export const updateVehicle = (id, data) =>
  api.put("/vehicle/" + id, data);

export const deleteVehicle = (id) =>
  api.delete("/vehicle/" + id);
