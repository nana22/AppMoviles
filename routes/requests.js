var mongo = require('mongodb');
var Server = mongo.Server;
var Db = mongo.Db;
var BSON = mongo.BSONPure;
 
var server = new Server('localhost', 27017, {auto_reconnect: true});
var db = new Db('aplicaciones', server);

db.open(function(err, db) {
    if(!err)
        console.log("Conectado a aplicaciones...");
    else
        console.log(err.message);
});

exports.verPropiedades = function(req, res) {
    db.collection('propiedades', function(err, collection) {
        collection.find({},{_id:0,fecha:0,isEnabled:0}).sort({fecha: -1}).toArray(function(err, items) {
            res.json(items);
        }); 
    });
};

exports.verDueniosXPropiedad = function(req, res) {
    db.collection('dueños', function(err, collection) {
        collection.find({"facebook": req.params.fbDuenio},{_id:0}).toArray(function(err, items) {
            res.json(items);
        });
    });
};

exports.verDuenios = function (req, res) {
    db.collection('dueños', function(err, collection) {
        collection.find({},{_id:0}).toArray(function(err, items) {
            res.json(items);
        });
    });
};

exports.verPropiedadesXDuenio = function (req, res) {
    db.collection('propiedades', function(err, collection) {
        collection.find({fbDueño: req.params.facebook},{_id:0, fecha:0, isEnabled:0}).sort({fecha:-1}).toArray(function(err, items) {
            res.json(items);
        });
    });
};