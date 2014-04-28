
/*
 * GET home page.
 */

var mongo = require('mongodb');
var Server = mongo.Server;
var Db = mongo.Db;
 
var server = new Server('localhost', 27017, {auto_reconnect: true});
var db = new Db('aplicaciones', server);

db.open(function(err, db) {});

exports.index = function(req, res) {
    if (req.user && req.user.user === 'Rudiney' && req.user.pass === 'Debaisu') {
        db.collection('propiedades', function(err, collection) {
            collection.find().sort({fecha:1}).toArray(function(err, propiedades) {
                db.collection('dueños', function(err, collection) {
                    collection.find().toArray(function(err, items) {
                        res.render('index', {dueños: items, props: propiedades});
                    }); 
                });
            }); 
        });
    } else {
        res.redirect("/autenticar");
    }
};

exports.logout = function (req, res) {
    req.logout();
    res.redirect('/autenticar');
};

exports.login = function(req, res) {
    if (req.user && req.user.user === 'Rudiney' && req.user.pass === 'Debaisu') {
        res.redirect('/');
    } else {
        res.render('login');
    }
};

exports.logError = function (req, res) {
    if (req.user && req.user.user === 'Rudiney' && req.user.pass === 'Debaisu') {
        res.redirect('/');
    } else {
        res.render('loginError');
    }
}