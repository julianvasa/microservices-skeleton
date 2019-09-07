We will not ask you to design a fancy algorithm. In the end that is not what you are going to do on a daily
basis if you work for us. Instead please deliver something production-ready. Use the tools developers use
when they work on the real thing. Focus on showing us how you would have solved a problem in your
daily work.
<br><br>
We ask you to provide a backend solution, ideally involving Spring Boot/Java 8. If you excel in
ops/devops you may want to invest in getting the software built and deployed properly.
<br><br>
Task:
<br>
Imagine a modular insurance product. People can choose from 4 modules. Each module has a different
selectable coverage and a different mathematical risk.
<br><br>
These are the modules:
- Bike (Coverage 0-3k, Risk 30%)
- Jewelry (Coverage 500-10k, Risk 5%)
- Electronics (Coverage 500-6k, Risk 35%)
- Sports Equipment (Coverage 0-20k, Risk 30%)
<br>

The user should be able to select the coverage for each module and see the calculated price. The price of
the tariff, which is the individual configuration for each customer, should be calculated based on the risk:
<br><br>
`[price] = [coverage]*[risk]`
<br><br>
The solution should store calculated prices and make them accessible via an endpoint.
<br><br>
Constraints:
<br>
You should not invest more than 6 hours on the task. Please deliver your solution within 7 days.
Please don’t publish the solution to your public github profile or something similar. Instead, send us a zip
file with the project.
