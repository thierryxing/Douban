const jsonServer = require('json-server')
const server = jsonServer.create()

// Support middleware
const middleware = jsonServer.defaults()
server.use(middleware)

// Add this before server.use(router)
server.use(jsonServer.rewriter({
  	'/api/v2/': '/',
  	'/group/user/:id/joined_groups': '/joined_groups',
  	'/group/:id/mixed_rec_groups': '/mixed_rec_groups',
  	'/user/:id': '/user',
	'/status/home_timeline': '/home_timeline'
}))

// 支持加载多个db文件
const _ = require('underscore')
const path = require('path')
const fs = require('fs')
const mockDir = path.join(__dirname, 'data')
const base = {}
const files = fs.readdirSync(mockDir)
files.forEach((file) => {
  _.extend(base, require(path.resolve(mockDir, file)))
})
const router = jsonServer.router(base)

server.use(router)

// 返回自定义格式数据
// router.render = (req, res) => {
//   res.jsonp({
//     data: res.locals.data,
//     status: 0,
//     message: ''
//   })
// }

server.listen(9090, () => {
  console.log('JSON Server is running')
})