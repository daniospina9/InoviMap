const express = require('express');
const bcrypt = require('bcryptjs');

const app = express();
app.use(express.json());

const users = []; // Temporary in-memory storage

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

    return res.json({ message: 'Login successful', user: { email } });
  } else {
    // User doesn't exist → create it
    const hashedPassword = await bcrypt.hash(password, 10);
    const newUser = { email, password: hashedPassword };
    users.push(newUser);

    return res.status(201).json({ message: 'User created successfully', user: { email } });
  }
});

// Simple route to check users (for testing only)
app.get('/users', (req, res) => {
  res.json(users.map(u => ({ email: u.email })));
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`🚀 Server running on http://localhost:${PORT}`));
