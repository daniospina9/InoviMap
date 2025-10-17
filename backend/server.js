const express = require('express');
const bcrypt = require('bcryptjs');

const app = express();
app.use(express.json());

const users = []; // Temporary in-memory storage

(async () => {
  const demoUsers = [
    { email: 'danielos@gmail.com', password: '123456', latitude: 48.8566, longitude: 2.3522 },
    { email: 'androidev@hotmail.com', password: 'new_project', latitude: 4.6097, longitude: -74.0817 },
  ];

  for (const u of demoUsers) {
    const hashed = await bcrypt.hash(u.password, 10);
    users.push({
      email: u.email,
      password: hashed,
      latitude: u.latitude,
      longitude: u.longitude
    });
  }
})();

// POST /login
app.post('/login', async (req, res) => {
  const { email, password } = req.body;

  if (!email || !password)
    return res.status(400).json({ error: 'Email and password are required' });

  const existingUser = users.find(u => u.email === email);

  if (existingUser) {
    // User exists → validate password
    const validPassword = await bcrypt.compare(password, existingUser.password);
    if (!validPassword)
      return res.status(401).json({ error: 'Invalid credentials' });

    return res.json({
      message: 'Login successful',
      user: {
        email: existingUser.email,
        latitude: existingUser.latitude,
        longitude: existingUser.longitude
      }
    });
  } else {
    // User doesn't exist → create it with default location (Bogotá)
    const hashedPassword = await bcrypt.hash(password, 10);
    const defaultLatitude = 4.7110;
    const defaultLongitude = -74.0721;

    const newUser = {
      email,
      password: hashedPassword,
      latitude: defaultLatitude,
      longitude: defaultLongitude
    };
    users.push(newUser);

    return res.status(201).json({
      message: 'User created successfully',
      user: {
        email: newUser.email,
        latitude: newUser.latitude,
        longitude: newUser.longitude
      }
    });
  }
});

// Simple route to check users (for testing only)
app.get('/users', (req, res) => {
  res.json(users.map(u => ({
    email: u.email,
    latitude: u.latitude,
    longitude: u.longitude
  })));
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`🚀 Server running on http://localhost:${PORT}`));