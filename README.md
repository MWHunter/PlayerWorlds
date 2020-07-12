This is a slightly modified version of the plugin found here: https://www.spigotmc.org/resources/%E2%AD%90-playerworldspro-%E2%AD%90-async-loading-expiration-economy-support-1-8-1-16.72113/

license of his plugin:
1. You certify that you are the authorized owner of the funds to purchase this plugin and cannot be withdrawn or refunded.
2. You are not permitted to sell, give, or redistribute this resource.
3. You are not allowed to use, view, or modify any plugin code.

However, his plugin uses the Bukkit API and is also therefore GPL, not whatever restrictive license he says it is.  I requested the source code for his plugin as per GPL, but he said that he does not give away source code.  I have uploaded this modified plugin onto github so others may recieve the same freedom as they should have been given.

Issues with PWP before I modified it:
- Dev will not give source code (So I decompiled and mapped it)
- Everything that should be done on the main thread is done async, such as GUI inventories (May be safe though)
- Everything that should be done async is done on the main thread, such as deleting world files and loading configs.
- Everything that should be async is done async incorrectly, such as creating a world, which held the main thread for two seconds anyways
- Everything that should be sync is done sync incorrectly, such as setting a player's gamemode sync before you load and teleport them async.
- GUI's were hard coded to the point of calling specific menus and every menu had it's own listener
- One listener was repeated three times
- Server freezes for two seconds on a world creation
- No nether or end support
- Per world inventories is found in a separate plugin and was not included in PlayerWorldsPro
- Worlds were infinite so expansion onto multiple servers would be difficult
- All worlds had to be loaded for per worlds inventories to work correctly
- Memory leaks

Issues with PWP after I modified it:
- Dev will not give source code (So I decompiled and mapped it)
- GUI's were hard coded to the point of calling specific menus and every menu had it's own listener
- No nether or end support
- Per world inventories is found in a separate plugin and was not included in PlayerWorldsPro
- Worlds were infinite so expansion onto multiple servers would be difficult
- All worlds had to be loaded for per worlds inventories to work correctly
- Memory leaks

I did make progress on this plugin, although I realized that it would be easier just to rewrite the plugin from scratch than deal with the mess created by _HeroPwP
