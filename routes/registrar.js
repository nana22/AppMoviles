
/*
 * GET home page.
 */

var mongo = require('mongodb');
var Server = mongo.Server;
var Db = mongo.Db;
 
var server = new Server('localhost', 27017, {auto_reconnect: true});
var db = new Db('aplicaciones', server);

db.open(function(err, db) {});

exports.regPropiedades = function(req, res) {
    if (req.user && req.user.user === 'Rudiney' && req.user.pass === 'Debaisu') {
        db.collection('dueños', function(err, collection) {
            collection.find().toArray(function(err, items) {
                console.log(req.user);
                res.render('regPropiedades', {dueños: items});
            }); 
        });
    }
    else
        res.redirect("/autenticar");
};

exports.regPersonas = function(req, res) {
    if (req.user && req.user.user === 'Rudiney' && req.user.pass === 'Debaisu') {
        db.collection('dueños', function(err, collection) {
            collection.find().toArray(function(err, items) {
                res.render('regPersonas', {personas: items});
            }); 
        });
    }
    else
        res.redirect("/autenticar");
};

exports.agregarPersona = function (req, res) {
	db.collection('dueños', function(err, collection) {
		if (req.body.facebook === "") { alert("Error"); }
        else collection.insert(req.body, {safe:true}, function(err, result) {});
    });
    res.redirect('http://localhost:3000/');
};

exports.agregarPropiedad = function (req, res) {
    var body = req.body;
    var json =  {
                    "tipo": body.tipo,
                    "contrato": body.contrato,
                    "precio": body.precio,
                    "fecha": new Date(),
                    "provincia": body.provincia,
                    "ciudad": body.ciudad,
                    "señal1": body.señal1,
                    "señal2": body.señal2,
                    "latitud": body.latitud,
                    "longitud": body.longitud,
                    "caracteristicas": body.caracteristicas,
                    "isEnabled": true,
                    "fbDueño": body.fbDueño
                };
	db.collection('propiedades', function(err, collection) {
        collection.insert(json, {safe:true}, function(err, result) {});
    });
};

exports.agregarPropiedadWS = function (req, res) {
    var body = req.body;
    if (body.fbDueño !== "") {
        var json =  {
                        "tipo": body.tipo,
                        "contrato": body.contrato,
                        "precio": body.precio,
                        "fecha": new Date(),
                        "provincia": body.provincia,
                        "ciudad": body.ciudad,
                        "señal1": body.señal1,
                        "señal2": body.señal2,
                        "latitud": body.latitud,
                        "longitud": body.longitud,
                        "isEnabled": true,
                        "caracteristicas": [body.general, body.estructura, body.amueblado, body.servicios],
                        "fbDueño": body.fbDueño
                    };
        db.collection('propiedades', function(err, collection) {
            collection.insert(json, {safe:true}, function(err, result) {});
        });
        res.redirect('http://localhost:3000/');
    }
}