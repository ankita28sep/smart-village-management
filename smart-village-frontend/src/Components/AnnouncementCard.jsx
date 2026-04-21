function AnnouncementCard({ announcement }) {
  return (
    <div className="card">
      <h3>{announcement.title}</h3>
      <p>{announcement.content}</p>
      <p><b>Type:</b> {announcement.type}</p>
    </div>
  );
}

export default AnnouncementCard;