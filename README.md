## Just enough scalaz

There are many scalaz introductions, but this one is mine. I focus on practical use of scalaz, for a scala developer who does not have prior knowledge of Haskell or monads. It turns out that scalaz can be very useful even if you are not so familiar with those concepts!

## Building

You should use sbt.  I recommend sbt, the rebel cut:

https://github.com/paulp/sbt-extras

run `sbt` and then `console` to execute code interactively. 

## References

I have annotated the code with URLs to pages, StackOverflow answers and tutorials I found during my research.

The following resources were helpful in particular:

- [scalaz for Dummies](http://bleibinha.us/blog/2014/12/scalaz-for-dummies)
- [Strings are untyped](https://www.seancassidy.me/strings-are-untyped.html) 
- [Accumulating More Than One Failure In A ValidationNEL](http://johnkurkowski.com/posts/accumulating-multiple-failures-in-a-ValidationNEL/)
- [StackOverflow: Access both the success and the failure from an aggregated ValidationNel](http://stackoverflow.com/a/32824369/7507)

## Questions

### What about Monads?

I decided not to delve into that topic, because I feel that focusing on that aspect of scalaz and functional programming in general often overshadows other, practical goals.

It's an outstanding TODO however.  Pull requests welcome!

### No one uses scalaz anymore. You should be learning about cats.

That's what I've heard! However, the Novus code base uses scalaz, and I suspect there are a lot of deployed applications in production at other places using it as well.

https://github.com/typelevel/cats

My goal here wasn't to necessarily promote scalaz, but rather learn enough to find my way around existing code. 

## TODO

- Cleanup imports in various locations
- Add examples for Mondads, Semigroups, etc.
- More canonical / complex Validation example. Perhaps "user input validation?"

## License

MIT License (c) 2016 Novus Partners, Inc and Noah Zucker
