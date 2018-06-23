'use strict';

const mongoose = require('mongoose');
const Schema=mongoose.Schema;
const userSchema=mongoose.Schema({
    name                :String,
    email               :String,
    hashed_password     :String,
    created_at          :String,
    temp_password       :String,
    temp_Password_time  :String
});

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/TjoonAlarm');

module.exports = mongoose.model('user', userSchema);
//export default mongoose.model('user', userSchema);