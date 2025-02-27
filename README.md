# RecursiveDegreePlanner
Program to assist in planning out course scheduling.

## Design Goals
| Goal | Status |
| :--- | ---: |
| Parse course list input | Planning |
| Parse requirement list input | Planning |
| (Optional) Scrape web data for course info | Planning |
| Simple CLI | Planning |
| On-prompt, deterministic schedule generation | Planning |
| Global schedule edit commands (maxCredits, maxSummerCredits) | Planning |
| Fine schedule edit commands (semesterMaxCredits, swap, set, blacklist) | Planning |
| Custom rules (courseXOR, courseSemesterLock) | Planning |
| (optional) boolean return with direct edit architecture | Planning |
| Full testing suite | Planning |

## Development Log

#### 2-27-2025 | Planning, Stage 1
At this stage in planning, two main concerns must be addressed to move forward:
 * Data collection. While course data has been requested from my University, it may not be available. While I await a response, I will look into methods to scrape the data.
 * Performance design. As I sketch various program structures, I am attempting to keep in mind complexity, expandability, flexibility, memory complexity, and computer performance.
   * Complexity and flexibility must be kept to a min/max respectively, so I will be heavily utilizing OOP features.
   * Memory vs Compute cost is a more complicated answer, which I'll discuss below.

First off, the problem is of rather limited scope, as the amount of courses available and to be scheduled is quite finite. That being said, purely bruteforcing this could still end up with somewhere in the ballpark upwards of `1e^100` combinations to compute. That's *possible*, but bad. 

This leads me to think on memory usage, but while this program could be coded with near zero auxilary space, the frankly miniscule scope makes me not too concerned. I would instead prefer the code simplicity allowed by taking up a bit more memory.

As such, I have settled on an approach using "prefilters" and "priority". All courses will be assigned various attributes (such as blacklisted, mandatory, preferred, etc), and upon each new schedule generation will be sorted. The schedule will then be loaded based on priority. This, in theory, allows the majority of operations to process in `O(n)` time, with a single `O(n^2)` sort operation done at creation.