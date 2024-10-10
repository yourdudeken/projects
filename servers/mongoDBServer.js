const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');

const app = express()

app.use(bodyParser.json());

const mongoURI = 'mongodb://localhost:27017/mydatabase';

mongoose.connect(mongoURI, { useNewUrlParser: true, useUnifiedTopology: true })
    .then(() => console.log('MongoDB connected...'))
    .catch(err => console.log(err));

const ItemSchema = new mongoose.Schema({
    name: String,
    quantity: Number
});

const Item = mongoose.model('Item', ItemSchema);

app.get('/items', async (req, res) => {
    try {
        const items = await Item.find();
        res.json(items);
    } catch (err) {
        res.status(500).send(err.message);
    }
});

app.post('/items', async (req, res) => {
    const newItem = new Item({
        name: req.body.name,
        quantity: req.body.quantity
    });

    try {
        const item = await newItem.save();
        res.json(item);
    } catch (err) {
        res.status(500).send(err.message);
    }
});

app.put('/items/:id', async (req, res) => {
    try {
        const item = await Item.findByIdAndUpdate(req.params.id, req.body, { new: true });
        res.json(item);
    } catch (err) {
        res.status(500).send(err.message);
    }
});

app.delete('/items/:id', async (req, res) => {
    try {
        await Item.findByIdAndDelete(req.params.id);
        res.json({ message: 'Item deleted' });
    } catch (err) {
        res.status(500).send(err.message);
    }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});