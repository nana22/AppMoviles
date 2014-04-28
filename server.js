
//Variables
var http = require('http');
var path = require('path');
var index = require('./routes');
var registrar = require('./routes/registrar');
var requests = require('./routes/requests');
var passport = require('passport');
var LocalStrategy = require('passport-local').Strategy;
var express = require('express');
var router = express();
var server = http.createServer(router);

// all environments
router.use(express.static(path.resolve(__dirname, 'client')));
router.set('views', path.join(__dirname, 'views'));
router.set('view engine', 'jade');
router.use(express.logger('dev'));
router.use(express.json());
router.use(express.urlencoded());
router.use(express.methodOverride());
router.use(express.static(path.join(__dirname, 'public')));
router.use(express.cookieParser());
router.use(express.bodyParser());
router.use(express.session({ secret: 'keyboard cat' }));
router.use(passport.initialize());
router.use(passport.session());
router.use(router.router);

passport.use(new LocalStrategy(
	function(username, password, done) {
        if(username === 'Rudiney' && password === 'Debaisu'){
            return done(null, {user: 'Rudiney', pass: 'Debaisu'});
        } else {
            return done(null, false, {message: 'El usuario no es correcto.'});
        }
    }
));

passport.serializeUser(function(user, done) {done(null, user);});
passport.deserializeUser(function(user, done) {done(null, user);});

router.get('/', index.index);
router.get('/autenticar', index.login);
router.get('/logerror', index.logError);
router.post('/login', passport.authenticate('local', { successRedirect: '/',
                                                    failureRedirect: '/logerror' }));
router.get('/logout', index.logout);
router.post('/addPerson', registrar.agregarPersona);
router.post('/addPropiedad', registrar.agregarPropiedad);
router.post('/addPropiedadWS', registrar.agregarPropiedadWS);
router.get('/regPropiedades', registrar.regPropiedades);
router.get('/regPersonas', registrar.regPersonas);
router.get('/verPropiedades', requests.verPropiedades);
router.get('/verDuenios', requests.verDuenios);
router.get('/verDueniosXPropiedad/:fbDuenio', requests.verDueniosXPropiedad);
router.get('/verPropiedadesXDuenio/:facebook', requests.verPropiedadesXDuenio);


server.listen(process.env.PORT || 3000, process.env.IP || "0.0.0.0", function(){
  var addr = server.address();
  console.log("Chat server listening at", addr.address + ":" + addr.port);
});
