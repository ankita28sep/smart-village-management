import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import SchemeCard from "../components/SchemeCard";
import AnnouncementCard from "../components/AnnouncementCard";
import {
  getActiveSchemes,
  getActiveAnnouncements,
  searchAnnouncements
} from "../services/api";

function Home() {
  const [schemes, setSchemes] = useState([]);
  const [announcements, setAnnouncements] = useState([]);
  const [search, setSearch] = useState("");

  useEffect(() => {
    loadData();
  }, []);

const loadData = async () => {
  try {
    const schemesData = await getActiveSchemes();
    const annData = await getActiveAnnouncements();

    // FIX: handle different response formats
    setSchemes(Array.isArray(schemesData) ? schemesData : schemesData.content || []);

    setAnnouncements(
      Array.isArray(annData) ? annData : annData.content || []
    );

  } catch (error) {
    console.error("Error loading data:", error);
    setSchemes([]);
    setAnnouncements([]);
  }
};const handleSearch = async () => {
  try {
    if (!search) return loadData();

    const result = await searchAnnouncements(search);

    setAnnouncements(
      Array.isArray(result) ? result : result.content || []
    );

  } catch (error) {
    console.error("Search error:", error);
  }
};

  return (
    <>
      <Navbar />

      <div className="container">
        <div style={{ marginTop: "20px" }}>
          <input
            type="text"
            placeholder="Search announcements..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            style={{ padding: "10px", width: "70%" }}
          />
          <button onClick={handleSearch} style={{ padding: "10px" }}>
            Search
          </button>
        </div>

        <h2 className="section-title">Active Announcements</h2>
       <div className="grid">
  {Array.isArray(announcements) &&
    announcements.map((a) => (
      <AnnouncementCard key={a.id} announcement={a} />
    ))}
</div>

        <h2 className="section-title">Government Schemes</h2>
       <div className="grid">
  {Array.isArray(schemes) &&
    schemes.map((s) => (
      <SchemeCard key={s.id} scheme={s} />
    ))}
</div>
      </div>
    </>
  );
}

export default Home;