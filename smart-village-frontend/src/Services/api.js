const API_URL = "http://localhost:8080/api";

export const getActiveSchemes = async () => {
  const res = await fetch(`${API_URL}/schemes/active`);
  return res.json();
};

export const getActiveAnnouncements = async () => {
  const res = await fetch(`${API_URL}/announcements/active`);
  return res.json();
};

export const searchAnnouncements = async (keyword) => {
  const res = await fetch(`${API_URL}/announcements/search?keyword=${keyword}`);
  return res.json();
};
export const loginUser = async (data) => {
  try {
    const res = await fetch(`${API_URL}/auth/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    });

    const result = await res.json();

    if (!res.ok) {
      throw new Error(result.message || "Login failed");
    }

    return result;

  } catch (error) {
    console.error("Login API error:", error);
    throw error;
  }
};