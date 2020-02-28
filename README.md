# A Spring Boot Application to Demonstrate Bug in Validation

Just upgraded Spring Boot from 2.1.4.RELEASE to 2.2.5.RELEASE and my application.yml doesn't validate any longer.

For example, in this yaml, the `itemOne` works in previous version but fails validation now.

    test:
      items:
        itemOne:
          COLOR: RED
        ITEM2:
          COLOR: BLUE


It seems any nested map key with uppercase letters will fail. Tried to debug and found `getName()` in
`ValidationBindHandler` mangles the key into

    test.items[item-one].color
    test.items[-i-t-e-m2].color

This is apparently incorrect.

