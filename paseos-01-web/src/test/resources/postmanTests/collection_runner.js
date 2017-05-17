var newman = require('newman'); // require newman in your project 
 
newman.run({
    collection: require('./paseos-01-prueba-fotos.json'),
}, function (err) {
    if (err) { throw err; }
    console.log('collection run complete!');
});

