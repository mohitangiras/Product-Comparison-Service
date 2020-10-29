db.auth('admin', 'pass');
db = db.getSiblingDB('products');

db.createUser({
    user: 'relayr-user',
    pwd: 'pass',
    roles: [
        {
            role: 'readWrite',
            db: 'products'
        }, {
            role: 'dbAdmin',
            db: 'products'
        }
    ]
});

db.test.insert({'name':'Mohit'})